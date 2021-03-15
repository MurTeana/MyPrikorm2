using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MyPrikormWebAPI.Interfaces;
using MyPrikormWebAPI.DB.Context;
using MyPrikormWebAPI.DB.Entities;

namespace MyPrikormWebAPI.Repositories
{
    public class UserRepository : IRepositoryUser
    {
        private ApplicationContext db;
        public UserRepository(ApplicationContext context)
        {
            db = context;
        }

        public async Task<List<User>> GetAll()
        {
            return await db.Users.ToListAsync();
        }

        public async Task<User> Get(int id)
        {
            return await db.Users.FirstOrDefaultAsync(x => x.Id == id);
        }

        public async Task<User> Create(User user)
        {
            if (user != null)
            {
                db.Users.Add(user);
                await db.SaveChangesAsync();
            }
            return user;
        }

        public async Task<User> Update(User user)
        {
            if (!db.Users.Any(x => x.Id == user.Id))
            {
                return null;
            }

            db.Update(user);
            await db.SaveChangesAsync();
            return user;
        }

        public async Task<User> Delete(int id)
        {
            User user = db.Users.FirstOrDefault(x => x.Id == id);
            if (user == null)
            {
                return user;
            }

            db.Users.Remove(user);
            await db.SaveChangesAsync();
            return user;
        }
    }
}
